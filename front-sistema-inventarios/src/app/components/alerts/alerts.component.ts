import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UtilsService } from '../../services/utils.service';
import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';
import { AlertClass, infoAlert } from 'src/app/models/alerts.model';

@Component({
  selector: 'app-alerts',
  styleUrls: ['./alerts.component.scss'],
  templateUrl: './alerts.component.html',
  animations: [
    trigger('fadeInOut', [
      state(
        'void',
        style({
          opacity: 0,
          transform: 'translateX(100%)',
        })
      ),
      transition('void <=> *', animate(300)),
    ]),
  ],
})
export class AlertsComponent implements OnInit {
  @ViewChild('alert', { static: true }) miDivRef!: ElementRef;
  alerts: infoAlert[] = []; // Cambia a un arreglo de alertas

  alertClasses: AlertClass = {
    info: 'fa-circle-info fa-beat',
    warning: 'fa-circle-exclamation fa-fade',
    success: 'fa-circle-check fa-bounce',
    danger: 'fa-circle-exclamation fa-fade',
    primary: 'fa-shield fa-flip',
    secundary: 'fa-gear fa-spin',
    light: 'fa-lightbulb fa-beat-fade',
  };

  constructor(private utilsService: UtilsService) {}

  ngOnInit() {
    this.utilsService.alert$.subscribe((alert) => {
      if (alert) {
        this.alerts.push(alert); // Agrega la nueva alerta al arreglo
        setTimeout(() => {
          this.clearAlert(alert);
        }, 5000);
      }
    });
  }

  clearAlert(alert: infoAlert) {
    const index = this.alerts.indexOf(alert);
    if (index !== -1) {
      this.alerts.splice(index, 1); // Elimina la alerta del arreglo
    }
  }
}
