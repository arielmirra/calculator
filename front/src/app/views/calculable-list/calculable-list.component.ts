import {Component, OnInit} from '@angular/core';
import {Calculable} from '../../models/Calculable';
import {MatDialog} from '@angular/material/dialog';
import {CalculableService} from '../../services/calculable.service';

@Component({
  selector: 'app-calculable-list',
  templateUrl: './calculable-list.component.html',
  styleUrls: ['./calculable-list.component.scss']
})
export class CalculableListComponent implements OnInit {
  calculables: Calculable[];

  constructor(private calculableService: CalculableService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

}
