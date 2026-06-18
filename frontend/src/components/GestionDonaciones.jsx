import { useEffect, useState } from 'react';
import api from '../services/api.js';
import SectionCard from './SectionCard.jsx';
import StatusMessage from './StatusMessage.jsx';

const initialEdit = {
  monto: '',
  fecha: '',
  estado: '',
  rutDonante: '',
  nombre: '',
  apellido: '',
  correo: '',
  telefono: ''
};

function GestionDonaciones() {
  const [buscarId, setBuscarId] = useState('');
  const [donacionBuscada, setDonacionBuscada] = useState(null);
  const [errorBusqueda, setErrorBusqueda] = useState('');
  const [donaciones, setDonaciones] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [editando, setEditando] = useState(null); // id de la donacion que se está editando
  const [formEdit, setFormEdit] = useState(initialEdit);

  const cargarDonaciones = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await api.get('/api/donaciones');
      setDonaciones(response.data);
    } catch (err) {
      setError('No se pudieron cargar las donaciones.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    cargarDonaciones();
  }, []);

  const handleEditarClick = (donacion) => {
    setEditando(donacion.idDon);
    setFormEdit({
      monto: donacion.monto,
      fecha: donacion.fecha,
      estado: donacion.estado,
      rutDonante: donacion.donante?.rutDonante || '',
      nombre: donacion.donante?.nombre || '',
      apellido: donacion.donante?.apellido || '',
      correo: donacion.donante?.correo || '',
      telefono: donacion.donante?.telefono || ''
    });
    setError('');
    setSuccess('');
  };

  const handleCancelar = () => {
    setEditando(null);
    setFormEdit(initialEdit);
  };

  const handleChangeEdit = (e) => {
    setFormEdit({ ...formEdit, [e.target.name]: e.target.value });
  };

  const handleGuardar = async (id) => {
    try {
      await api.put(`/api/donaciones/${id}`, {
        monto: Number(formEdit.monto),
        fecha: formEdit.fecha || null,
        estado: formEdit.estado,
        donante: {
          rutDonante: formEdit.rutDonante,
          nombre: formEdit.nombre,
          apellido: formEdit.apellido,
          correo: formEdit.correo,
          telefono: formEdit.telefono
        }
      });
      setSuccess('Donacion actualizada correctamente.');
      setEditando(null);
      cargarDonaciones();
    } catch (err) {
      setError('No se pudo actualizar la donacion.');
    }
  };
  const handleBuscarPorId = async () => {
    if (!buscarId) return;
    setErrorBusqueda('');
    setDonacionBuscada(null);
    try {
      const response = await api.get(`/api/donaciones/${buscarId}`);
      setDonacionBuscada(response.data);
    } catch (err) {
      setErrorBusqueda(`No se encontró ninguna donación con ID ${buscarId}.`);
    }
  };
  
  const handleLimpiarBusqueda = () => {
    setBuscarId('');
    setDonacionBuscada(null);
    setErrorBusqueda('');
  };

  const handleEliminar = async (id) => {
    if (!window.confirm('¿Estás seguro de que deseas eliminar esta donacion?')) return;
    try {
      await api.delete(`/api/donaciones/${id}`);
      setSuccess('Donacion eliminada correctamente.');
      cargarDonaciones();
    } catch (err) {
      setError('No se pudo eliminar la donacion.');
    }
  };

  return (
    <SectionCard title="Gestion de Donaciones" description="Listado administrativo de donaciones registradas.">
      <StatusMessage error={error} success={success} />

      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-outline-success" type="button" onClick={cargarDonaciones}>
          Actualizar listado
        </button>
      </div>
      <div className="d-flex gap-2 mb-3">
  <input
    className="form-control w-auto"
    type="number"
    placeholder="Buscar por ID..."
    value={buscarId}
    onChange={(e) => setBuscarId(e.target.value)}
  />
  <button className="btn btn-primary" onClick={handleBuscarPorId}>
    Buscar
  </button>
  {donacionBuscada && (
    <button className="btn btn-outline-secondary" onClick={handleLimpiarBusqueda}>
      Limpiar
    </button>
  )}
</div>

{errorBusqueda && <div className="alert alert-warning">{errorBusqueda}</div>}

{donacionBuscada && (
  <div className="alert alert-info mb-3">
    <strong>Resultado:</strong> ID {donacionBuscada.idDon} — ${donacionBuscada.monto} —{' '}
    {donacionBuscada.fecha} — {donacionBuscada.estado} —{' '}
    {donacionBuscada.donante?.nombre} {donacionBuscada.donante?.apellido} —{' '}
    {donacionBuscada.donante?.correo}
  </div>
)}

      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th>ID</th>
              <th>Monto</th>
              <th>Fecha</th>
              <th>Estado</th>
              <th>RUT donante</th>
              <th>Donante</th>
              <th>Correo</th>
              <th>Telefono</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {donaciones.map((donacion) =>
              editando === donacion.idDon ? (
                // FILA EN MODO EDICION
                <tr key={donacion.idDon}>
                  <td>{donacion.idDon}</td>
                  <td>
                    <input className="form-control form-control-sm" name="monto" type="number" min="1"
                      value={formEdit.monto} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <input className="form-control form-control-sm" name="fecha" type="date"
                      value={formEdit.fecha} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <select className="form-select form-select-sm" name="estado" value={formEdit.estado} onChange={handleChangeEdit}>
                      <option value="Aprobada">Aprobada</option>
                      <option value="Pendiente">Pendiente</option>
                      <option value="Rechazada">Rechazada</option>
                    </select>
                  </td>
                  <td>
                    <input className="form-control form-control-sm" name="rutDonante"
                      value={formEdit.rutDonante} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <input className="form-control form-control-sm mb-1" name="nombre" placeholder="Nombre"
                      value={formEdit.nombre} onChange={handleChangeEdit} />
                    <input className="form-control form-control-sm" name="apellido" placeholder="Apellido"
                      value={formEdit.apellido} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <input className="form-control form-control-sm" name="correo" type="email"
                      value={formEdit.correo} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <input className="form-control form-control-sm" name="telefono"
                      value={formEdit.telefono} onChange={handleChangeEdit} />
                  </td>
                  <td>
                    <button className="btn btn-success btn-sm me-1" onClick={() => handleGuardar(donacion.idDon)}>
                      Guardar
                    </button>
                    <button className="btn btn-secondary btn-sm" onClick={handleCancelar}>
                      Cancelar
                    </button>
                  </td>
                </tr>
              ) : (
                // FILA NORMAL
                <tr key={donacion.idDon}>
                  <td>{donacion.idDon}</td>
                  <td>${donacion.monto}</td>
                  <td>{donacion.fecha}</td>
                  <td>{donacion.estado}</td>
                  <td>{donacion.donante?.rutDonante}</td>
                  <td>{donacion.donante?.nombre} {donacion.donante?.apellido}</td>
                  <td>{donacion.donante?.correo}</td>
                  <td>{donacion.donante?.telefono}</td>
                  <td>
                    <button className="btn btn-warning btn-sm me-1" onClick={() => handleEditarClick(donacion)}>
                      Editar
                    </button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleEliminar(donacion.idDon)}>
                      Eliminar
                    </button>
                  </td>
                </tr>
              )
            )}
            {!loading && donaciones.length === 0 && (
              <tr>
                <td colSpan="9" className="text-center text-muted">No hay donaciones registradas.</td>
              </tr>
            )}
            {loading && (
              <tr>
                <td colSpan="9" className="text-center text-muted">Cargando donaciones...</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </SectionCard>
  );
}

export default GestionDonaciones;