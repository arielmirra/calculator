import {Component, OnInit} from '@angular/core';
import {Calculable} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {UpdateCalculableDialogComponent} from '../../dialogs/update-calculable-dialog/update-calculable-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-calculable-list',
  templateUrl: './calculable-list.component.html',
  styleUrls: ['./calculable-list.component.scss']
})
export class CalculableListComponent implements OnInit {
  calculables: Calculable[];

  constructor(
    private calculableService: CalculableService,
    private snackbar: SnackbarService,
    private router: Router,
    private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.calculableService.fetchAll().subscribe(list => this.calculables = list);
  }

  calculate(calc: Calculable): void {
    this.calculableService.calculate(calc._id).subscribe(result => this.snackbar.openSnackbar(`El resultado del cálculo es ${result}`));
  }

  createCalc(): void {
    this.router.navigate(['calculable/new']);
  }

  openDialog(calculable: Calculable): void {
    const dialogRef = this.dialog.open(UpdateCalculableDialogComponent, {
      width: '400px',
      data: {actual: calculable, calculables: this.calculables}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.calculableService.updateCalculable(result, result.id).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Cálculo guardado satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }
}
