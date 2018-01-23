import { Component } from '@angular/core';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent {
  private article;
  private articleText;
  private counter = 0;
  private text: string[] = [];
  private interval;
  constructor() {
    this.article = '该不该搁下重重的壳.' + '寻找到底哪里有蓝天.' + '随着轻轻的风轻轻地飘.' + '历经的伤都不感觉疼.'
      + '我要一步一步往上爬.' + '等待阳光静静看着它的脸.' + '小小的天有大大的梦想.' + '重重的壳裹着轻轻地仰望.'
      + '我要一步一步往上爬.' + '在最高点乘着叶片往前飞.' + '小小的天流过的泪和汗.' + '总有一天我有属于我的天.'
      + '我要一步一步往上爬.' + '在最高点乘着叶片往前飞.' + '任风吹干流过的泪和汗.' + '我要一步一步往上爬.'
      + '等待阳光静静看着它的脸.' + '小小的天有大大的梦想.' + '我有属于我的天.' + '任风吹干流过的泪和汗.'
      + '总有一天我有属于我的天.';
    this.print();
  }

  private print() {
    let line = 0;
    let lineText = '';
    this.text.push(lineText);
    this.interval = setInterval(() => {
      if (this.counter <= this.article.length) {
        const c = this.article[this.counter];
        if (c !== '.') {
          lineText += c;
          this.text[line] = lineText + '_';
        } else {
          this.text[line] = lineText.slice(0, lineText.length);
          line += 1;
          lineText = '';
          if (this.counter + 1 < this.article.length) {
            this.text.push(lineText);
          }
        }
        this.counter++;
      }else {
        this.text.pop();
        clearInterval(this.interval);
      }
    }, 400);
  }
}
