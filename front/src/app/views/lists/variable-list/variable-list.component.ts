import {Component, OnInit} from '@angular/core';
import {Calculable, CalculableType} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';
import {UpdateCalculableDialogComponent} from '../../dialogs/update-calculable-dialog/update-calculable-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-calculable-list',
  templateUrl: './variable-list.component.html',
  styleUrls: ['./variable-list.component.scss']
})
export class VariableListComponent implements OnInit {
  variables: Calculable[];

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
    this.calculableService.fetchAll().subscribe(list => {
      console.log(list);
      this.variables = list.filter(c => c.calculableType === CalculableType.VARIABLE);
    });
  }

  createCalc(): void {
    this.router.navigate(['calculable/new']);
  }

  openDialog(calculable: Calculable): void {
    const dialogRef = this.dialog.open(UpdateCalculableDialogComponent, {
      width: '400px',
      data: {actual: calculable, calculables: this.variables}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.calculableService.updateCalculable(result, result.id).subscribe(success => {
        if (success) {
          this.snackbar.openSnackbar('Variable guardada satisfactoriamente');
          this.fetch();
        } else {
          this.snackbar.openSnackbar('No se ha podido guardar los cambios');
        }
      });
    });
  }
}
