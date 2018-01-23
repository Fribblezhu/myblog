import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';

export function isPresent(obj: any) {
    return obj !== undefined && obj !== null;
}

export enum LoadingBarEventType {
    PROGRESS,
    HEIGHT,
    COLOR,
    VISIBLE
}

export class LoadingBarEvent {
    constructor(public type: LoadingBarEventType, public value: any) { }
}

@Injectable()
export class LoadingBarService {

    private _progress: number = 0;
    private _height: string = '2px';
    private _color: string = 'firebrick';
    private _visible: boolean = true;

    private _intervalCounterId: any = 0;

    private eventSource: Subject<LoadingBarEvent> = new Subject<LoadingBarEvent>();

    private interval: number = 500; // in milliseconds
    private events: Observable<LoadingBarEvent> = this.eventSource.asObservable();

    public onLoadingBarEvent(fn: (event: LoadingBarEvent) => void): void {
        this.events.subscribe(fn);
    }

    set progress(value: number) {
        if (isPresent(value)) {
            if (value > 0) {
                this.visible = true;
            }
            this._progress = value;
            this.emitEvent(new LoadingBarEvent(LoadingBarEventType.PROGRESS, this._progress));
        }
    }

    get progress(): number {
        return this._progress;
    }

    set height(value: string) {
        if (isPresent(value)) {
            this._height = value;
            this.emitEvent(new LoadingBarEvent(LoadingBarEventType.HEIGHT, this._height));
        }
    }

    get height(): string {
        return this._height;
    }

    set color(value: string) {
        if (isPresent(value)) {
            this._color = value;
            this.emitEvent(new LoadingBarEvent(LoadingBarEventType.COLOR, this._color));
        }
    }

    get color(): string {
        return this._color;
    }

    set visible(value: boolean) {
        if (isPresent(value)) {
            this._visible = value;
            this.emitEvent(new LoadingBarEvent(LoadingBarEventType.VISIBLE, this._visible));
        }
    }

    get visible(): boolean {
        return this._visible;
    }

    public start(onCompleted: Function = null) {
        // Stop current timer
        this.stop();
        // Make it visible for sure
        this.visible = true;
        // Run the timer with milliseconds iterval
        this._intervalCounterId = setInterval(() => {
            // Increment the progress and update view component
            this.progress++;
            // If the progress is 100% - call complete
            if (this.progress === 100) {
                this.complete();
            }
        }, this.interval);
    }

    public stop() {
        if (this._intervalCounterId) {
            clearInterval(this._intervalCounterId);
            this._intervalCounterId = null;
        }
    }

    public reset() {
        this.stop();
        this.progress = 0;
    }

    public complete() {
        this.progress = 100;
        this.stop();
        setTimeout(() => {
            // Hide it away
            this.visible = false;
            setTimeout(() => {
                // Drop to 0
                this.progress = 0;
            }, 250);
        }, 250);
    }

    private emitEvent(event: LoadingBarEvent) {
        if (this.eventSource) {
            // Push up a new event
            this.eventSource.next(event);
        }
    }

}
