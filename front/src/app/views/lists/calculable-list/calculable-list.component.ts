import {Component, OnInit} from '@angular/core';
import {Calculable} from '../../../models/Calculable';
import {CalculableService} from '../../../services/calculable.service';
import {Router} from '@angular/router';
import {SnackbarService} from '../../../services/snackbar.service';

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
    private router: Router) {
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
}
