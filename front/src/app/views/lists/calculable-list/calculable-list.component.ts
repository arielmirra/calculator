import {Component, OnInit} from '@angular/core';
import {Calculable, CalculableForm} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {UpdateDialogComponent} from '../../dialogs/update-dialog/update-dialog.component';
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
    const dialogRef = this.dialog.open(UpdateDialogComponent, {
      width: '250px',
      data: {actual: calculable, calculables: this.calculables}
    });

    dialogRef.afterClosed().subscribe(result => {
      const form = CalculableForm.empty();
      form.name = result.name;
      form.value = result.value;
      form.left = result.left;
      form.right = result.right;
      form.operator = result.operator;
      this.calculableService.updateCalculable(form, result._id).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Cálculo guardado satisfactoriamente');
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }
}
