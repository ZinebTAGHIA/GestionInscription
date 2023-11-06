import React from "react";
import { NavLink } from "react-router-dom";

const Sidebar = (props) => {
  const handleMouseEnter = () => {
    const allSideDivider = document.querySelectorAll("#sidebar .divider");

    if (props.isSidebarHiden) {
      const activeLinks = document.querySelector(".side-menu a.active");
      if (activeLinks) {
      }

      allSideDivider.forEach((item) => {
        item.textContent = item.dataset.text;
      });
    }
  };

  const handleMouseLeave = () => {
    const allSideDivider = document.querySelectorAll("#sidebar .divider");

    if (props.isSidebarHiden) {
      document.querySelectorAll(".side-menu li a").forEach((a) => {});

      allSideDivider.forEach((item) => {
        item.textContent = "-";
      });
    }
  };

  return (
    <section
      id="sidebar"
      className={props.isSidebarHiden ? "hide" : ""}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
    >
      <img
        id="user"
        src={"https://cdn-icons-png.flaticon.com/512/149/149071.png"}
        alt="Photo"
      />

      <h5 id="role" style={{ color: "grey" }}>
        ADMIN
      </h5>
      <ul className="side-menu">
        <li>
          <NavLink
            to="/"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bxs-dashboard icon" /> Accueil
          </NavLink>
        </li>
        <li className="divider" data-text="demandes">
          {props.isSidebarHiden ? "-" : "Filières"}
        </li>
        <li>
          <NavLink
            to="/add-filiere"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-plus icon" />
            Ajouter une filière
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/filieres-list"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-table icon" />
            Liste des filières
          </NavLink>
        </li>
        <li className="divider" data-text="comptes">
          {props.isSidebarHiden ? "-" : "Rôles"}
        </li>
        <li>
          <NavLink
            to="/add-role"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-plus icon" />
            Ajouter un rôle
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/roles-list"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-table icon" />
            Liste des rôles
          </NavLink>
        </li>
        <li className="divider" data-text="profil">
          {props.isSidebarHiden ? "-" : "étudiants"}
        </li>
        <li>
          <NavLink
            to="/add-etud"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bxs-user-plus icon" />
            Ajouter un étudiant
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/etuds-list"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-group icon" />
            Liste des étudiants
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/etuds-filiere"
            className={({ isActive }) => (isActive ? "active" : "")}
          >
            <i className="bx bx-search-alt-2 icon" />
            Chercher par filière
          </NavLink>
        </li>
      </ul>
    </section>
  );
};

export default Sidebar;
