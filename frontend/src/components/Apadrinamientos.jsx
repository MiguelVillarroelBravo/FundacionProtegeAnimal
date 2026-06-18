import { useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

const initialForm = {
  idAnimal: '',
  fkRutPadrino: '',
  montoMensual: '',
  fechaInicio: '',
  estado: 'ACTIVO'
};

function Apadrinamientos() {
  const [form, setForm] = useState(initialForm);
  const [rutBusqueda, setRutBusqueda] = useState('');
  const [apadrinamientos, setApadrinamientos] = useState([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const crearApadrinamiento = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    const payload = {
      idAnimal: Number(form.idAnimal),
      fkRutPadrino: form.fkRutPadrino,
      montoMensual: Number(form.montoMensual),
      fechaInicio: form.fechaInicio || null,
      estado: form.estado || null
    };

    try {
      await api.post('/api/apadrinamientos', payload);
      setForm(initialForm);
      setSuccess('Apadrinamiento registrado correctamente.');
      if (rutBusqueda) {
        buscarPorRut();
      }
    } catch (err) {
      setError('No se pudo registrar el apadrinamiento.');
    }
  };

  const buscarPorRut = async (event) => {
    if (event) {
      event.preventDefault();
    }
    setError('');
    setSuccess('');

    try {
      const response = await api.get(`/api/apadrinamientos/padrino/${rutBusqueda}`);
      setApadrinamientos(response.data);
    } catch (err) {
      setError('No se pudieron cargar los apadrinamientos del padrino.');
    }
  };

  return (
    <SectionCard title="Apadrinamientos" description="Registra un apadrinamiento y consulta los activos por RUT.">
      <StatusMessage error={error} success={success} />

      <form className="row g-3 mb-4" onSubmit={crearApadrinamiento}>
        <div className="col-md-3">
          <label className="form-label">ID animal</label>
          <input className="form-control" name="idAnimal" type="number" min="1" value={form.idAnimal} onChange={handleChange} required />
        </div>
        <div className="col-md-3">
          <label className="form-label">RUT padrino</label>
          <input className="form-control" name="fkRutPadrino" value={form.fkRutPadrino} onChange={handleChange} maxLength="12" required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Monto mensual</label>
          <input className="form-control" name="montoMensual" type="number" min="1" value={form.montoMensual} onChange={handleChange} required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Fecha inicio</label>
          <input className="form-control" name="fechaInicio" type="date" value={form.fechaInicio} onChange={handleChange} />
        </div>
        <div className="col-md-3">
          <label className="form-label">Estado</label>
          <input className="form-control" name="estado" value={form.estado} onChange={handleChange} />
        </div>
        <div className="col-12">
          <button className="btn btn-success" type="submit">Registrar apadrinamiento</button>
        </div>
      </form>

      <form className="row g-3 mb-4 search-box" onSubmit={buscarPorRut}>
        <div className="col-md-8">
          <label className="form-label">Buscar por RUT padrino</label>
          <input className="form-control" value={rutBusqueda} onChange={(event) => setRutBusqueda(event.target.value)} maxLength="12" required />
        </div>
        <div className="col-md-4 d-flex align-items-end">
          <button className="btn btn-outline-success w-100" type="submit">Buscar apadrinamientos</button>
        </div>
      </form>

      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th>ID</th>
              <th>ID animal</th>
              <th>RUT padrino</th>
              <th>Monto mensual</th>
              <th>Fecha inicio</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            {apadrinamientos.map((item) => (
              <tr key={item.idApadrinamiento}>
                <td>{item.idApadrinamiento}</td>
                <td>{item.idAnimal}</td>
                <td>{item.fkRutPadrino}</td>
                <td>${item.montoMensual}</td>
                <td>{item.fechaInicio}</td>
                <td>{item.estado}</td>
              </tr>
            ))}
            {apadrinamientos.length === 0 && (
              <tr>
                <td colSpan="6" className="text-center text-muted">Busca un RUT para ver apadrinamientos activos.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </SectionCard>
  );
}

export default Apadrinamientos;
