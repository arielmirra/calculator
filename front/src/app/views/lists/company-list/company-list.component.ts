import { Component, OnInit } from '@angular/core';
import {SnackbarService} from '../../../services/snackbar.service';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Company} from '../../../models/Company';
import {CompanyService} from '../../../services/company.service';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {
  companies: Company[];

  constructor(
    private companyService: CompanyService,
    private snackbar: SnackbarService,
    public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch(): void {
    this.companyService.fetchAll().subscribe(list => {
      console.log(list);
      this.companies = list;
    });
  }

  createCompany(): void {
    this.router.navigate(['company/new']);
  }

}
