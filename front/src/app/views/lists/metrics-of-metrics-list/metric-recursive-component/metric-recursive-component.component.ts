import {Component, Input, OnInit} from '@angular/core';
import {Calculable, Operator} from '../../../../models/Calculable';
import {FormulaModalComponent} from '../../metric-list/formula-modal/formula-modal.component';
import {CalculableService} from '../../../../services/calculable.service';
import {FormulaService} from '../../../../services/formula.service';
import {SnackbarService} from '../../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Measurement} from '../../../../models/Measurement';

@Component({
  selector: 'app-metric-recursive-component',
  templateUrl: './metric-recursive-component.component.html',
  styleUrls: ['./metric-recursive-component.component.scss']
})
export class MetricRecursiveComponentComponent implements OnInit {

  @Input() metric: Calculable;
  lastMeasurement: Measurement;
  Operator = Operator;

  constructor(
    private calculableService: CalculableService,
    private formulaService: FormulaService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  seeMetrics(metric: Calculable): void {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/metric/metrics/' + metric.id]);
  }

  measure(m: Calculable): void {
    this.calculableService.measure(m.id).subscribe(formula => {
      const dialogRef = this.dialog.open(FormulaModalComponent, {
        width: '550px',
        data: {formula}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
        this.formulaService.measure(formula.fromId, formula).subscribe(res => {
          if (res){
            this.lastMeasurement = res;
            console.log(this.lastMeasurement);
          } else {
            console.log('ERROR');
          }
        });
      });
    });
  }

}
