import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import "./styles/dashboard.css";

const Dashboard = ({ user }) => {
  useEffect(() => {
    const allProgress = document.querySelectorAll("main .card .progress");

    allProgress.forEach((item) => {
      item.style.setProperty("--value", item.dataset.value);
    });
  }, []);

  return (
    <main>
      <h1 className="title">Accueil</h1>
      <ul className="breadcrumbs">
        <li>
          <Link to="/">Accueil</Link>
        </li>
        <li className="divider">/</li>
        <li>
          <a href="#" className="active">
            Accueil
          </a>
        </li>
      </ul>
      <div className="info-data">
        <Link to="/add-filiere">
          <div className="card">
            <div className="head">
              <div>
                <i className="bx bxs-book-alt bx-md"></i>
                <p>Gestion des filières</p>
              </div>
            </div>
          </div>
        </Link>
        <Link to="/add-role">
          <div className="card">
            <div className="head">
              <div>
                <i className="bx bx-shield bx-md"></i>
                <p>Gestion des rôles</p>
              </div>
            </div>
          </div>
        </Link>
      </div>
      <div className="info-data">
        <Link to="/add-etud">
          <div className="card">
            <div className="head">
              <div>
                <i className="bx bx-user bx-md"></i>
                <p>Gestion des étudiants</p>
              </div>
            </div>
          </div>
        </Link>
      </div>
    </main>
  );
};

export default Dashboard;
