import { useState } from 'react';
import Donaciones from './components/Donaciones.jsx';
import GestionDonaciones from './components/GestionDonaciones.jsx';
import Inventario from './components/Inventario.jsx';
import Voluntariado from './components/Voluntariado.jsx';
import Apadrinamientos from './components/Apadrinamientos.jsx';
import Alertas from './components/Alertas.jsx';
import logoFundacion from './assets/LogoFundacion.png';

const publicSections = [
  { id: 'donaciones', label: 'Donaciones', component: Donaciones },
  { id: 'apadrinamientos', label: 'Apadrinamientos', component: Apadrinamientos },
  { id: 'voluntariado', label: 'Voluntariado', component: Voluntariado }
];

const adminSections = [
  { id: 'gestion-donaciones', label: 'Gestion de Donaciones', component: GestionDonaciones },
  { id: 'inventario', label: 'Inventario', component: Inventario },
  { id: 'alertas', label: 'Alertas', component: Alertas }
];

function App() {
  const [activeRole, setActiveRole] = useState('publico');
  const [activePublicSection, setActivePublicSection] = useState(publicSections[0].id);
  const [activeAdminSection, setActiveAdminSection] = useState(adminSections[0].id);

  const visibleSections = activeRole === 'publico' ? publicSections : adminSections;
  const activeSection = activeRole === 'publico' ? activePublicSection : activeAdminSection;
  const CurrentSection = visibleSections.find((section) => section.id === activeSection).component;

  const selectSection = (sectionId) => {
    if (activeRole === 'publico') {
      setActivePublicSection(sectionId);
      return;
    }
    setActiveAdminSection(sectionId);
  };

  return (
    <div className="app-shell">
      <nav className="navbar navbar-expand-lg navbar-dark bg-success">
        <div className="container navbar-content">
          <div className="brand-area">
            <img
              className="brand-logo"
              src={logoFundacion}
              alt="Logo Fundacion Protege Animal"
            />
            <span className="navbar-brand fw-semibold">Fundacion Protege Animal</span>
          </div>
          {activeRole === 'publico' ? (
            <button className="btn btn-light btn-sm" type="button" onClick={() => setActiveRole('admin')}>
              Ingresar como administrador
            </button>
          ) : (
            <button className="btn btn-light btn-sm" type="button" onClick={() => setActiveRole('publico')}>
              Volver al portal publico
            </button>
          )}
        </div>
      </nav>

      <main className="container py-4">
        <div className="page-title mb-4">
          <h1>{activeRole === 'publico' ? 'Portal publico' : 'Administracion interna'}</h1>
          <p>
            {activeRole === 'publico'
              ? 'Acceso para donaciones, apadrinamientos y voluntariado.'
              : 'Gestion interna de donaciones, inventario y alertas.'}
          </p>
        </div>

        <div className="nav nav-pills section-nav mb-4">
          {visibleSections.map((section) => (
            <button
              className={`nav-link ${activeSection === section.id ? 'active' : ''}`}
              key={section.id}
              type="button"
              onClick={() => selectSection(section.id)}
            >
              {section.label}
            </button>
          ))}
        </div>

        <CurrentSection />
      </main>
    </div>
  );
}

export default App;
