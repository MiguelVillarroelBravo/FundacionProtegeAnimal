import { useEffect, useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

const initialForm = {
  tipoAlerta: '',
  mensaje: '',
  fechaGeneracion: '',
  leida: false,
  idReferencia: ''
};

function Alertas() {
  const [alertas, setAlertas] = useState([]);
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const cargarAlertas = async () => {
    setError('');
    try {
      const response = await api.get('/api/alertas');
      setAlertas(response.data);
    } catch (err) {
      setError('No se pudieron cargar las alertas.');
    }
  };

  useEffect(() => {
    cargarAlertas();
  }, []);

  const handleChange = (event) => {
    const { name, value, type, checked } = event.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  };

  const crearAlerta = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    const payload = {
      tipoAlerta: form.tipoAlerta,
      mensaje: form.mensaje,
      fechaGeneracion: form.fechaGeneracion || null,
      leida: form.leida,
      idReferencia: Number(form.idReferencia)
    };

    try {
      await api.post('/api/alertas', payload);
      setForm(initialForm);
      setSuccess('Alerta registrada correctamente.');
      cargarAlertas();
    } catch (err) {
      setError('No se pudo registrar la alerta.');
    }
  };

  return (
    <SectionCard title="Alertas" description="Consulta alertas no leidas y registra nuevas alertas.">
      <StatusMessage error={error} success={success} />

      <form className="row g-3 mb-4" onSubmit={crearAlerta}>
        <div className="col-md-3">
          <label className="form-label">Tipo alerta</label>
          <input className="form-control" name="tipoAlerta" value={form.tipoAlerta} onChange={handleChange} required />
        </div>
        <div className="col-md-5">
          <label className="form-label">Mensaje</label>
          <input className="form-control" name="mensaje" value={form.mensaje} onChange={handleChange} maxLength="255" required />
        </div>
        <div className="col-md-2">
          <label className="form-label">ID referencia</label>
          <input className="form-control" name="idReferencia" type="number" min="1" value={form.idReferencia} onChange={handleChange} required />
        </div>
        <div className="col-md-2">
          <label className="form-label">Fecha</label>
          <input className="form-control" name="fechaGeneracion" type="datetime-local" value={form.fechaGeneracion} onChange={handleChange} />
        </div>
        <div className="col-12">
          <div className="form-check">
            <input className="form-check-input" id="leida" name="leida" type="checkbox" checked={form.leida} onChange={handleChange} />
            <label className="form-check-label" htmlFor="leida">Crear como leida</label>
          </div>
        </div>
        <div className="col-12">
          <button className="btn btn-success" type="submit">Registrar alerta</button>
        </div>
      </form>

      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tipo</th>
              <th>Mensaje</th>
              <th>Fecha</th>
              <th>Referencia</th>
              <th>Leida</th>
            </tr>
          </thead>
          <tbody>
            {alertas.map((alerta) => (
              <tr key={alerta.idAlerta}>
                <td>{alerta.idAlerta}</td>
                <td>{alerta.tipoAlerta}</td>
                <td>{alerta.mensaje}</td>
                <td>{alerta.fechaGeneracion}</td>
                <td>{alerta.idReferencia}</td>
                <td>{alerta.leida ? 'Si' : 'No'}</td>
              </tr>
            ))}
            {alertas.length === 0 && (
              <tr>
                <td colSpan="6" className="text-center text-muted">No hay alertas no leidas.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </SectionCard>
  );
}

export default Alertas;
