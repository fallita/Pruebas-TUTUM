import axios from "axios";
const config = {
    headers: { Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMCwiaXNzIjoiS2FpemVudGVjTVgiLCJpYXQiOjE2NzYwMDU2OTgsInVzZXJuYW1lIjoic3VwZXJfdXN1YXJpbyJ9.SbzsJySna2kT9EFW1Bb9e5M2hbVx7cy47MpORppdzd0' }
};


export class PersonaService{

    

    //baseUrl="http://localhost:56388/api"
    baseUrl="http://localhost:8080/v3"
    //http://localhost:8080/v3/calificaciones?alumno=1&rows=100
    
    create(empleado){
        return axios.post(this.baseUrl+"/calificaciones",empleado,config).then(res => res.data)
    }

    update(empleado){
        return axios.put(this.baseUrl+"/calificaciones",empleado,config).then(res => res.data)
    }

    delete(id){
        return axios.delete(this.baseUrl+"/calificaciones/"+id,config).then(res => res.data)
    }

    getAll(){
        return axios.get(this.baseUrl+"/calificaciones?alumno=1&rows=100",config)
        .then(res => res.data)
/**
        .then((responseData) => {
            console.log("Console log de products responseData");
            console.log(responseData);
        })
        .catch(function(e) {
            console.log(e);
        })
        **/
    }
}