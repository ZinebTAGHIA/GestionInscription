import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import "./components/SharedLayout";
import SharedLayout from "./components/SharedLayout";
import AddFiliere from "./pages/AddFiliere";
import ListeFilieres from "./pages/ListeFilieres";
import AddRole from "./pages/AddRole";
import ListeRoles from "./pages/ListeRoles";
import AddEtudiant from "./pages/AddEtudiant";
import ListeEtudiants from "./pages/ListeEtudiants";
import EtudiantsByFiliere from "./pages/EtudiantsByFiliere";
import Error from "./pages/Error";
import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<SharedLayout />}>
            <Route index element={<Dashboard />} />
            <Route path="add-filiere" element={<AddFiliere />} />
            <Route path="filieres-list" element={<ListeFilieres />} />
            <Route path="add-role" element={<AddRole />} />
            <Route path="roles-list" element={<ListeRoles />} />
            <Route path="add-etud" element={<AddEtudiant />} />
            <Route path="etuds-list" element={<ListeEtudiants />} />
            <Route path="etuds-filiere" element={<EtudiantsByFiliere />} />
          </Route>

          <Route path="*" element={<Error />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
