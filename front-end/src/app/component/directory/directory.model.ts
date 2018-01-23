export interface Directory {
  id: string;
  name: string;
  items: Directory[];
}


export interface File {
  id: string;
  name: string;
  create_time: string;
  description: string;
}

