import SectionCard from './SectionCard.jsx';

function Inicio() {
  return (
    <SectionCard
      title="Bienvenido"
      description="Portal publico para colaborar con la Fundacion Protege Animal."
    >
      <div className="row g-3">
        <div className="col-md-4">
          <div className="info-panel">
            <h3>Donaciones</h3>
            <p>Apoya directamente el cuidado de animales mediante aportes registrados en el sistema.</p>
          </div>
        </div>
        <div className="col-md-4">
          <div className="info-panel">
            <h3>Apadrinamiento</h3>
            <p>Registra un apadrinamiento y consulta los aportes activos asociados a un RUT.</p>
          </div>
        </div>
        <div className="col-md-4">
          <div className="info-panel">
            <h3>Voluntariado</h3>
            <p>Revisa actividades disponibles e inscribete como voluntario.</p>
          </div>
        </div>
      </div>
    </SectionCard>
  );
}

export default Inicio;
