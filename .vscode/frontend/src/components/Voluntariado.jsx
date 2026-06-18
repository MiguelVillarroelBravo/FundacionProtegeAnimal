import { useEffect, useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

function Voluntariado() {
  const [actividades, setActividades] = useState([]);
  const [rutVoluntario, setRutVoluntario] = useState('');
  const [actividadSeleccionada, setActividadSeleccionada] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const cargarActividades = async () => {
    setError('');
    try {
      const response = await api.get('/api/voluntariado/actividades');
      setActividades(response.data);
    } catch (err) {
      setError('No se pudieron cargar las actividades.');
    }
  };

  useEffect(() => {
    cargarActividades();
  }, []);

  const inscribir = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    try {
      await api.post('/api/voluntariado/inscribir', {
        idActividad: Number(actividadSeleccionada),
        rutVoluntario,
        asistenciaConfirmada: false
      });
      setRutVoluntario('');
      setActividadSeleccionada('');
      setSuccess('Inscripcion registrada correctamente.');
      cargarActividades();
    } catch (err) {
      setError('No se pudo registrar la inscripcion. Puede que la actividad no tenga cupos.');
    }
  };

  return (
    <SectionCard title="Voluntariado" description="Revisa actividades disponibles e inscribete como voluntario.">
      <StatusMessage error={error} success={success} />

      <form className="row g-3 mb-4" onSubmit={inscribir}>
        <div className="col-md-5">
          <label className="form-label">Actividad</label>
          <select className="form-select" value={actividadSeleccionada} onChange={(event) => setActividadSeleccionada(event.target.value)} required>
            <option value="">Seleccionar actividad</option>
            {actividades.map((actividad) => (
              <option key={actividad.idActividad} value={actividad.idActividad}>
                {actividad.nombreActividad}
              </option>
            ))}
          </select>
        </div>
        <div className="col-md-5">
          <label className="form-label">RUT voluntario</label>
          <input className="form-control" value={rutVoluntario} onChange={(event) => setRutVoluntario(event.target.value)} maxLength="12" required />
        </div>
        <div className="col-md-2 d-flex align-items-end">
          <button className="btn btn-success w-100" type="submit">Inscribirme</button>
        </div>
      </form>

      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th>ID</th>
              <th>Actividad</th>
              <th>Fecha</th>
              <th>Cupos</th>
            </tr>
          </thead>
          <tbody>
            {actividades.map((actividad) => (
              <tr key={actividad.idActividad}>
                <td>{actividad.idActividad}</td>
                <td>{actividad.nombreActividad}</td>
                <td>{actividad.fecha}</td>
                <td>{actividad.cuposDisponibles}</td>
              </tr>
            ))}
            {actividades.length === 0 && (
              <tr>
                <td colSpan="4" className="text-center text-muted">No hay actividades disponibles.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </SectionCard>
  );
}

export default Voluntariado;
