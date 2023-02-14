import React, { useContext, useState } from "react";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {Panel} from "primereact/panel";
import {Button} from "primereact/button";
import { EmpleadoContext } from "../contexts/EmpleadoContext";
import EmpleadoForm from "./EmpleadoForm";

const EmpleadoList = () => {

    const {empleados, findEmpleado} = useContext(EmpleadoContext);

    const [isVisible, setIsVisible] = useState(false);

    const saveEmpleado = (id) => {
        findEmpleado(id)
        setIsVisible(true)
    }

    const footer = (
        <div className="p-clearfix"  style={{width:"100%"}}>
            <Button
                style={{float:"left"}}
                icon="pi pi-plus"
                label="Add"
                onClick={() => setIsVisible(true)}
            />

        </div>
    )

    return (<div>
 <Panel header="Lista de Calificaciones" style={{width:'80%', marginTop:'20px', margin:'0 auto'}}>
        <DataTable 
                value={empleados}
                selectionMode="single"
                onSelectionChange={(e) => saveEmpleado(e.value.idTCalificaciones)}
                footer={footer}
                >
          <Column field="idTCalificaciones" header="idTCalificaciones"></Column>
          <Column field="alumnoId" header="IdAlumno"></Column>
          <Column field="alumnoNombre" header="Alumno"></Column>
          <Column field="materiaId" header="IdMateria"></Column>
          <Column field="materiaNombre" header="Materia"></Column>
          <Column field="calificacion" header="Calificación"></Column>
          <Column field="fechaRegistro" header="Fecha"></Column>
        </DataTable>
      </Panel>
      
  <EmpleadoForm isVisible={isVisible} setIsVisible={setIsVisible}/>
    </div>);
}

export default EmpleadoList;