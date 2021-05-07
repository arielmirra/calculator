import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(private snackbar: MatSnackBar, private router: Router) {
  }

  public openSnackbar(message: string, actionMessage?: string, actionLink?: string, ms?: number): void {
    const snackbarRef = this.snackbar.open(message, actionMessage || 'X', {
      duration: ms || 3000,
      verticalPosition: 'top'
    });

    snackbarRef.onAction().subscribe(() => {
      if (actionLink){
        this.router.navigate([actionLink]);
      }
    });
  }
}
