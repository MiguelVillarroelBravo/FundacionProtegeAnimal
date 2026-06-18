import { useEffect, useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

const initialForm = {
  nombre: '',
  tipoInsumo: '',
  stockActual: '',
  stockMinimo: '',
  unidadMedida: ''
};

function Inventario() {
  const [insumos, setInsumos] = useState([]);
  const [form, setForm] = useState(initialForm);
  const [mermas, setMermas] = useState({});
  const [idBusqueda, setIdBusqueda] = useState('');
  const [insumoEncontrado, setInsumoEncontrado] = useState(null);
  const [idEliminar, setIdEliminar] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const cargarInsumos = async () => {
    setError('');
    try {
      const response = await api.get('/api/inventario');
      setInsumos(response.data);
    } catch (err) {
      setError('No se pudo cargar el inventario.');
    }
  };

  useEffect(() => {
    cargarInsumos();
  }, []);

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const crearInsumo = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    const payload = {
      nombre: form.nombre,
      tipoInsumo: form.tipoInsumo,
      stockActual: Number(form.stockActual),
      stockMinimo: Number(form.stockMinimo),
      unidadMedida: form.unidadMedida
    };

    try {
      await api.post('/api/inventario', payload);
      setForm(initialForm);
      setSuccess('Insumo registrado correctamente.');
      cargarInsumos();
    } catch (err) {
      setError('No se pudo registrar el insumo.');
    }
  };

  const aplicarMerma = async (id) => {
    const cantidad = Number(mermas[id]);
    if (!cantidad || cantidad <= 0) {
      setError('Ingresa una cantidad de merma mayor a cero.');
      return;
    }

    setError('');
    setSuccess('');
    try {
      await api.put(`/api/inventario/${id}/merma`, null, { params: { cantidad } });
      setMermas({ ...mermas, [id]: '' });
      setSuccess('Merma aplicada correctamente.');
      cargarInsumos();
    } catch (err) {
      setError('No se pudo aplicar la merma.');
    }
  };

  const buscarPorId = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');
    setInsumoEncontrado(null);

    try {
      const response = await api.get(`/api/inventario/${idBusqueda}`);
      setInsumoEncontrado(response.data);
      setSuccess('Insumo encontrado correctamente.');
    } catch (err) {
      setError('No se encontro un insumo con ese ID.');
    }
  };

  const eliminarPorId = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    try {
      await api.delete(`/api/inventario/${idEliminar}`);
      setIdEliminar('');
      setInsumoEncontrado(null);
      setSuccess('Insumo eliminado correctamente.');
      cargarInsumos();
    } catch (err) {
      setError('No se pudo eliminar el insumo. Revisa que el ID exista.');
    }
  };

  return (
    <SectionCard title="Inventario" description="Gestion interna de insumos, busqueda por ID, merma y eliminacion.">
      <StatusMessage error={error} success={success} />

      <div className="row g-3 mb-4">
        <div className="col-lg-6">
          <form className="admin-tool" onSubmit={buscarPorId}>
            <label className="form-label">Buscar insumo por ID</label>
            <div className="input-group">
              <input
                className="form-control"
                type="number"
                min="1"
                value={idBusqueda}
                onChange={(event) => setIdBusqueda(event.target.value)}
                required
              />
              <button className="btn btn-outline-success" type="submit">Buscar</button>
            </div>
          </form>
        </div>
        <div className="col-lg-6">
          <form className="admin-tool" onSubmit={eliminarPorId}>
            <label className="form-label">Eliminar insumo por ID</label>
            <div className="input-group">
              <input
                className="form-control"
                type="number"
                min="1"
                value={idEliminar}
                onChange={(event) => setIdEliminar(event.target.value)}
                required
              />
              <button className="btn btn-outline-danger" type="submit">Eliminar</button>
            </div>
          </form>
        </div>
      </div>

      {insumoEncontrado && (
        <div className="found-item mb-4">
          <h3>Resultado de busqueda</h3>
          <div className="row g-2">
            <div className="col-md-2"><strong>ID:</strong> {insumoEncontrado.idInsumo}</div>
            <div className="col-md-3"><strong>Nombre:</strong> {insumoEncontrado.nombre}</div>
            <div className="col-md-2"><strong>Tipo:</strong> {insumoEncontrado.tipoInsumo}</div>
            <div className="col-md-2"><strong>Stock:</strong> {insumoEncontrado.stockActual} {insumoEncontrado.unidadMedida}</div>
            <div className="col-md-3"><strong>Minimo/Critico:</strong> {insumoEncontrado.stockMinimo} / {insumoEncontrado.stockCritico}</div>
          </div>
        </div>
      )}

      <form className="row g-3 mb-4" onSubmit={crearInsumo}>
        <div className="col-md-4">
          <label className="form-label">Nombre</label>
          <input className="form-control" name="nombre" value={form.nombre} onChange={handleChange} required />
        </div>
        <div className="col-md-4">
          <label className="form-label">Tipo de insumo</label>
          <input className="form-control" name="tipoInsumo" value={form.tipoInsumo} onChange={handleChange} required />
        </div>
        <div className="col-md-4">
          <label className="form-label">Unidad medida</label>
          <input className="form-control" name="unidadMedida" value={form.unidadMedida} onChange={handleChange} required />
        </div>
        <div className="col-md-4">
          <label className="form-label">Stock actual</label>
          <input className="form-control" name="stockActual" type="number" min="0" step="0.1" value={form.stockActual} onChange={handleChange} required />
        </div>
        <div className="col-md-4">
          <label className="form-label">Stock minimo</label>
          <input className="form-control" name="stockMinimo" type="number" min="0" step="0.1" value={form.stockMinimo} onChange={handleChange} required />
        </div>
        <div className="col-md-4">
          <label className="form-label">Stock critico</label>
          <input className="form-control" name="stockCritico" type="number" min="0" step="0.1" value={form.stockCritico} onChange={handleChange} required />
        </div>
        <div className="col-12">
          <button className="btn btn-success" type="submit">Registrar insumo</button>
        </div>
      </form>

      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Tipo</th>
              <th>Stock</th>
              <th>Minimo</th>
              <th>Critico</th>
              <th>Merma</th>
            </tr>
          </thead>
          <tbody>
            {insumos.map((insumo) => (
              <tr key={insumo.idInsumo}>
                <td>{insumo.idInsumo}</td>
                <td>{insumo.nombre}</td>
                <td>{insumo.tipoInsumo}</td>
                <td>{insumo.stockActual} {insumo.unidadMedida}</td>
                <td>{insumo.stockMinimo}</td>
                <td>{insumo.stockCritico}</td>
                <td>
                  <div className="input-group input-group-sm">
                    <input
                      className="form-control"
                      type="number"
                      min="0"
                      step="0.1"
                      value={mermas[insumo.idInsumo] || ''}
                      onChange={(event) => setMermas({ ...mermas, [insumo.idInsumo]: event.target.value })}
                    />
                    <button className="btn btn-outline-success" type="button" onClick={() => aplicarMerma(insumo.idInsumo)}>
                      Aplicar
                    </button>
                  </div>
                </td>
              </tr>
            ))}
            {insumos.length === 0 && (
              <tr>
                <td colSpan="7" className="text-center text-muted">No hay insumos registrados.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </SectionCard>
  );
}

export default Inventario;
