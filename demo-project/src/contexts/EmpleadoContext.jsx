import React, {createContext, useState, useEffect} from 'react';
import { PersonaService } from '../service/PersonaService';

export const EmpleadoContext = createContext();

const EmpleadoContextProvider = (props) => {
   
    const materiasData = [
        { 
            id: 1, 
            materia: "matematicas" 
        },
        { 
            id: 2, 
            materia: "programacion I" 
        },
        { 
            id: 3, 
            materia: "ingenieria de sofware" 
        }
    ];

    const empleadoService = new PersonaService();

const [empleados,setEmpleados] = useState([]);
const [materias,setMaterias] = useState([]);

const [editEmpleado,setEditEmpleado] = useState(null);
const [editMateria,setEditMateria] = useState(null);




useEffect(() => {
    empleadoService.getAll().then(data => setEmpleados(data));
    setMaterias(materiasData);
},[]);

const createEmpleado = (empleado) =>{
    empleadoService
    .create(empleado)
    .then((data) => setEmpleados([...empleados, data]))
}

const deleteEmpleado = (idTCalificaciones) =>{
    empleadoService
    .delete(idTCalificaciones)
    .then(() => setEmpleados(empleados.filter((p) => p.idTCalificaciones !== idTCalificaciones )))
}

const findEmpleado = (idTCalificaciones) =>{
    const empleado = empleados.find(p => p.idTCalificaciones === idTCalificaciones);
    setEditEmpleado(empleado);
    // console.log(empleado.materiaId)
    // console.log(materias)
    const materia = { 
        id: empleado.materiaId, 
        materia: empleado.materiaNombre 
    }
    
    //materias.find(p => p.id === empleado.materiaId);
    //console.log(materia)
    setEditMateria(materia);
    
}

const updateEmpleado = (empleado) =>{

    console.log(empleado)
    console.log(empleados)

    empleadoService
    .update(empleado)
    .then((data) => 
    setEmpleados(
        empleados.map(p => p.idTCalificaciones === empleado.idTCalificaciones ? empleado : p)
        ));

    console.log(empleados)
    setEditEmpleado(null);
    setEditMateria(null);
}

    return (
        <EmpleadoContext.Provider
        value={{
            createEmpleado,
            deleteEmpleado,
            findEmpleado,
            updateEmpleado,
            editEmpleado,
            empleados,
            materias,
            editMateria
        }}
        >
            {props.children}
        
        </EmpleadoContext.Provider>
    );
};

export default EmpleadoContextProvider;