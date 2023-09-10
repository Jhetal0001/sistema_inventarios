import { Component, OnInit } from '@angular/core';
import { UtilsService } from '../../services/utils.service';

@Component({
  selector: 'app-loader',
  templateUrl: `./loader.component.html`,
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent implements OnInit{

  loading$ = this.load.loadind$;
  constructor(private load: UtilsService) {}

  ngOnInit() {
    this.loading$.subscribe((loading) => {
      if (loading) {
        setTimeout(() => {
          this.load.hideLoad();
        }, 40000);
      }
    });
  }
}
