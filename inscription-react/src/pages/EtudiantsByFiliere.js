import React, { useEffect, useRef, useState } from "react";
import axios from "../api/axios";
import "primereact/resources/themes/saga-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { InputText } from "primereact/inputtext";
import { FilterMatchMode } from "primereact/api";
import { Link } from "react-router-dom";
import { Dropdown } from "primereact/dropdown";
import "./styles/list.css";
import { Panel } from "primereact/panel";
import { classNames } from "primereact/utils";

const EtudiantsByFiliere = () => {
  const [data, setData] = useState();
  const [filieres, setFilieres] = useState([]);
  const [selectFiliere, setSelectFiliere] = useState("");
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
  });

  useEffect(() => {
    axios
      .get(`/api/filieres`)
      .then((response) => {
        setFilieres(response.data);
        console.log(response.data);
      })
      .catch((error) => console.error(error));
  }, []);

  useEffect(() => {
    if (selectFiliere) {
      axios
        .get(`/api/students/filiere/${selectFiliere}`)
        .then((response) => {
          setData(response.data);
          console.log(response.data);
        })
        .catch((error) => console.error(error));
    }
  }, [selectFiliere]);

  const onGlobalFilterChange = (event) => {
    const value = event.target.value;
    let _filters = { ...filters };

    _filters["global"].value = value;

    setFilters(_filters);
  };

  const renderHeader = () => {
    const value = filters["global"] ? filters["global"].value : "";

    return (
      <span className="p-input-icon-left">
        <i className="pi pi-search" />
        <InputText
          type="search"
          value={value || ""}
          onChange={(e) => onGlobalFilterChange(e)}
          placeholder="Global Search"
        />
      </span>
    );
  };

  const header = renderHeader();

  return (
    <div>
      <main>
        <>
          <h1 className="title">Etudiants</h1>
          <ul className="breadcrumbs">
            <li>
              <Link to="/">Accueil</Link>
            </li>
            <li className="divider">/</li>
            <li>
              <a href="#" className="active">
                Liste des étudiants
              </a>
            </li>
          </ul>
          <Panel header="Sélectionner une filière">
            <Dropdown
              id="filiere"
              value={selectFiliere}
              onChange={(e) => setSelectFiliere(e.value)}
              options={filieres.map((filiere) => {
                return {
                  label: filiere.name,
                  value: filiere.id,
                };
              })}
            ></Dropdown>
          </Panel>
          <DataTable
            value={data}
            header={header}
            filters={filters}
            paginator
            rows={10}
            emptyMessage="Aucun étudiant trouvée."
          >
            <Column field="id" header="ID" sortable />
            <Column field="lastName" header="Nom" />
            <Column field="firstName" header="Prénom" />
            <Column field="phone" header="Tel" />
            <Column
              field={(rowData) => rowData.filiere.name}
              header="Filière"
            />
          </DataTable>
        </>
      </main>
    </div>
  );
};

export default EtudiantsByFiliere;
