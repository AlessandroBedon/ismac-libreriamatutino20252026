import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import Swal from "sweetalert2";

import { Categoria } from "../../model/categoria.model";
import { CategoriaService } from "../../services/categoria";

@Component({
    selector: 'app-categoria',
    standalone: false,
    templateUrl: './categoria.html',
    styleUrl: './categoria.css',
})

export class CategoriaComponent implements OnInit {
    @ViewChild('formularioCategoria') formularioCategoria!: ElementRef;
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;

    categorias: Categoria[] = [];
    categoria: Categoria = {} as Categoria;

    editar: boolean = false;
    idEditar: number | null = null;

    dataSource!: MatTableDataSource<Categoria>;
    mostrarColumnas: string[] = [
        'idCategoria',
        'categoria',
        'descripcion',
        'acciones'
    ];

    constructor(private categoriaService: CategoriaService) {}

    ngOnInit(): void {
        this.findAll();
    }

    findAll(): void {
        this.categoriaService.findAll().subscribe((data: Categoria[]) => {
            this.dataSource = new MatTableDataSource(data);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
        });
    }

    save(): void {
        this.categoriaService.save(this.categoria).subscribe(() => {
            this.categoria = {} as Categoria;
            this.findAll();
        });
    }

    update(): void {
        if (this.idEditar !== null) {
            this.categoriaService.update(this.idEditar, this.categoria).subscribe(() => {
                this.categoria = {} as Categoria;
                this.editar = false;
                this.idEditar = null;
                this.findAll();
            });
        }
    }

    delete(): void{
  Swal.fire({
      title: '¿Desea eliminar el dato?',
      text: 'Esta opcion no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.categoriaService.delete(this.categoria.idCategoria).subscribe(() => {
          this.findAll();
          this.categoria = {} as Categoria;
          Swal.fire('Eliminado', 'La categoría fue eliminada', 'success');
        });
      } else {
        this.categoria = {} as Categoria;
      }
    });
  }

  // Interaccion en la pagina web
  editarCategoria(categoria: Categoria): void {
    this.categoria = { ...categoria };
    this.idEditar = categoria.idCategoria;
    this.editar = true;

    setTimeout(() => {
      this.formularioCategoria.nativeElement.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
      });
    }, 100);
  }

  editarCategoriaCancelar(form: NgForm): void {
    this.categoria = {} as Categoria;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardar(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    } else {
      this.save();
      form.resetForm();
    }
  }

  applyFilter(event: Event) {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
}