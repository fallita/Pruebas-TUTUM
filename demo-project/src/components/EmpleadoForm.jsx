import React, {useContext, useState, useEffect} from 'react';
import { EmpleadoContext } from '../contexts/EmpleadoContext';
import {Dialog} from "primereact/dialog";
import {Button} from "primereact/button";
import {InputText} from "primereact/inputtext";
import {InputNumber} from "primereact/inputnumber";
import {Calendar} from "primereact/calendar";
import { Dropdown } from 'primereact/dropdown';

const EmpleadoForm = (props) =>{

    
    const initialMateriaState = {
        id: 1,
        materia:"matematicas"
    }
    


    const [selectedMateria, setSelectedMateria] = useState(initialMateriaState);

    const {isVisible, setIsVisible} = props;
    const {
        createEmpleado,
        deleteEmpleado,
        editEmpleado,
        updateEmpleado,
        materias,
        editMateria
    } = useContext(EmpleadoContext);

    const initialEmpleadoState = {
        idTCalificaciones: 0,
        alumnoId:"1",
        materiaId:1,
        alumnoNombre:"Jhon",
        materiaNombre: "matematicas",
        calificacion: "",
        fechaRegistro: null,
    }
    
    const [empleadoData, setEmpleadoData] = useState(initialEmpleadoState);

    useEffect(() =>{
        if(editMateria) 
        {
           setEmpleadoData(editEmpleado)
           // console.log(editMateria)
            setSelectedMateria(editMateria)
        };
    }, [editMateria])


    const updateField = (data, field) =>{
        setEmpleadoData({
            ...empleadoData,
            [field]:data
        })

        //console.log(empleadoData)

    }

    const updateFieldCombo = (data, field) =>{
        

        setEmpleadoData({
             ...empleadoData,
             [field]:data.id,
             ["materiaNombre"]:data.materia
         })

        //console.log(data)
        // console.log(field)
        // console.log(fieldCombo)
        // console.log(selectedMateria)

        setSelectedMateria(data)

        //console.log(selectedMateria)

    }

    const _deleteEmpleado = () => {
        if (editEmpleado) {
            deleteEmpleado(empleadoData.idTCalificaciones);
          setEmpleadoData(initialEmpleadoState);
          setSelectedMateria(initialMateriaState);
        }
        setIsVisible(false);
      };

    const saveEmpleado = () => {
      
    if (!editEmpleado) {
            createEmpleado(empleadoData);
        } else {
            updateEmpleado(empleadoData);
          }

        setEmpleadoData(initialEmpleadoState);
        setSelectedMateria(initialMateriaState);
        setIsVisible(false);
    }

    const dialogFooter = (
        <div className='ui-dialog-buttonpane p-clearfix'>
        <Button label="Delete" icon="pi pi-times" onClick={_deleteEmpleado} />
            <Button label='Save' icon="pi pi-check" onClick={saveEmpleado}/>
        </div>

    )

    const clearSelected = () => {
        setIsVisible(false);
        setEmpleadoData(initialEmpleadoState);
        setSelectedMateria(initialMateriaState);
      };
    

return (<div>
    <Dialog
        visible={isVisible}
        modal={true}
        style={{width:"420px"}}
        contentStyle={{overflow:"visible"}}
        header="Calificación"
        onHide={() => clearSelected()}
        footer={dialogFooter}
    >
        <div className='p-grid p-fluid'>
            <br/>
            <div className='p-float-label'>
                <InputText
                    value={empleadoData.alumnoNombre}
                    onChange={e => updateField(e.target.value.trim(), "nombreCompleto")}
                    readOnly={true}
                />
                <label>Alumno:</label>
            </div>
            <br/>
            <div className='p-float-label'>
            <Dropdown 
            value={selectedMateria} 
            onChange={e => updateFieldCombo(e.value, "materiaId")}
            //onChange={(e) => setSelectedMateria(e.value)}
            options={materias} 
            optionLabel="materia" 
            inputId="id"
            //placeholder="Select a City" 
            className="w-full md:w-14rem" />
                <label>Materia:</label>
            </div>
            <br/>
            <div className='p-float-label'>
                <InputText
                    value={empleadoData.calificacion}
                    onChange={e => updateField(e.target.value, "calificacion")}
                   // mode="currency"
                    //currency='USD'
                />
                <label>Calificación:</label>
            </div>
            <br/>
            <div className='p-float-label'>
                <InputText
                    value={empleadoData.alumnoId}
                    onChange={e => updateField(e.target.value.trim(), "alumnoId")}
                    readOnly={true}
                />
                <label>AlumnoId:</label>
            </div>
            <br/>
            <div className='p-float-label'>
                
                 <InputText
                    value={empleadoData.fechaRegistro}
                    onChange={e => updateField(e.target.value, "fechaRegistro")}
                    readOnly={true}
                   // dateFormat="yy-mm-dd"
                />
                <label>Fecha de Registro:</label>
            </div>
        </div>
    </Dialog>
</div>);
}


export default EmpleadoForm;