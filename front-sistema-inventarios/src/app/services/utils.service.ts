import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { infoAlert } from '../models/alerts.model';

@Injectable({
  providedIn: 'root',
})
export class UtilsService {
  private alerts: infoAlert[] = [];
  alert$: BehaviorSubject<infoAlert | null> =
    new BehaviorSubject<infoAlert | null>(null);

  constructor() {}
  private loadingSubject = new Subject<any>();
  loadind$ = this.loadingSubject.asObservable();

  private lightboxSubject = new Subject<any>();
  lightbox$ = this.lightboxSubject.asObservable();

  showAlert(message: string, type: string) {
    const alert: infoAlert = { message, type };
    this.alerts.push(alert);
    this.alert$.next(alert);
    setTimeout(() => {
      this.clearAlert(alert);
    }, 5000);
  }

  clearAlert(alert: infoAlert) {
    const index = this.alerts.indexOf(alert);
    if (index !== -1) {
      this.alerts.splice(index, 1);
      this.alert$.next(null);
    }
  }

  showLoad() {
    this.loadingSubject.next(true);
  }

  hideLoad() {
    this.loadingSubject.next(false);
  }
}
