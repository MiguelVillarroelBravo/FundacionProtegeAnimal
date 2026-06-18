import { useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

const initialForm = {
  monto: '',
  rutDonante: '',
  nombre: '',
  apellido: '',
  correo: '',
  telefono: ''
};

function Donaciones() {
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    const payload = {
      monto: Number(form.monto),
      fecha: form.fecha || null,
      estado: form.estado || null,
      donante: {
        rutDonante: form.rutDonante,
        nombre: form.nombre,
        apellido: form.apellido,
        correo: form.correo,
        telefono: form.telefono
      }
    };

    try {
      await api.post('/api/donaciones', payload);
      setForm(initialForm);
      setSuccess('Donacion registrada correctamente.');
    } catch (err) {
      setError('No se pudo registrar la donacion. Revisa los datos ingresados.');
    }
  };

  return (
    <SectionCard title="Donaciones" description="Completa el formulario para realizar una donacion.">
      <StatusMessage error={error} success={success} />

      <form className="row g-3" onSubmit={handleSubmit}>
        <div className="col-md-3">
          <label className="form-label">Monto</label>
          <input className="form-control" name="monto" type="number" min="1" value={form.monto} onChange={handleChange} required />
        </div>
        
        <div className="col-md-3">
          <label className="form-label">RUT donante</label>
          <input className="form-control" name="rutDonante" value={form.rutDonante} onChange={handleChange} maxLength="12" required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Nombre</label>
          <input className="form-control" name="nombre" value={form.nombre} onChange={handleChange} required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Apellido</label>
          <input className="form-control" name="apellido" value={form.apellido} onChange={handleChange} required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Correo</label>
          <input className="form-control" name="correo" type="email" value={form.correo} onChange={handleChange} required />
        </div>
        <div className="col-md-3">
          <label className="form-label">Telefono</label>
          <input className="form-control" name="telefono" value={form.telefono} onChange={handleChange} required />
        </div>
        <div className="col-12">
          <button className="btn btn-success" type="submit">Registrar donacion</button>
        </div>
      </form>
    </SectionCard>
  );
}

export default Donaciones;
