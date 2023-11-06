import React, { useEffect, useState } from "react";
import { Form, Field } from "react-final-form";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import axios from "../api/axios";
import { Dialog } from "primereact/dialog";
import { classNames } from "primereact/utils";
import "./styles/form.css";
import { Link } from "react-router-dom";
import { Panel } from "primereact/panel";
import "./styles/form.css";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primereact/resources/primereact.min.css";
import { Password } from "primereact/password";
import { MultiSelect } from "primereact/multiselect";

const AddEtudiant = () => {
  const [showMessage, setShowMessage] = useState(false);
  const [formData, setFormData] = useState({});
  const [filieres, setFilieres] = useState([]);
  const [roles, setRoles] = useState([]);
  const [selectRoles, setSelectRoles] = useState([]);

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
    axios
      .get(`/api/roles`)
      .then((response) => {
        setRoles(response.data);
        console.log(response.data);
      })
      .catch((error) => console.error(error));
  }, []);

  const validate = (data) => {
    let errors = {};
    data.roles = selectRoles;
    if (!data.lastName) {
      errors.lastName = "Nom est obligatoire.";
    }
    if (!data.firstName) {
      errors.firstName = "Prénom est obligatoire.";
    }
    if (!data.phone) {
      errors.phone = "Tel est obligatoire.";
    }
    if (!data.login) {
      errors.login = "Login est obligatoire.";
    }
    if (!data.password) {
      errors.password = "Password est obligatoire.";
    }
    if (!data.filiere) {
      errors.filiere = "Filière est obligatoire.";
    }
    if (!data.roles || data.roles.length === 0) {
      errors.roles = "Roles est obligatoire.";
    }
    if (!data.email) {
      errors.email = "Email est obligatoire.";
    }
    if (data.email) {
      if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(data.email)) {
        errors.email = "Adresse email invalide. E.g. example@email.com";
      }
    }
    return errors;
  };

  const onSubmit = (data, form) => {
    console.log(data);
    data.roles = selectRoles;
    setFormData(data);
    if (data) {
      axios
        .post(`/api/students`, {
          lastName: data.lastName,
          firstName: data.firstName,
          phone: data.phone,
          filiere: {
            id: data.filiere,
          },
          roles: data.roles.map((id) => {
            return { id: id };
          }),
          email: data.email,
          login: data.login,
          password: data.password,
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
      setShowMessage(true);
    }
    setSelectRoles([]);
    form.restart();
  };

  const isFormFieldValid = (meta) => !!(meta.touched && meta.error);
  const getFormErrorMessage = (meta) => {
    return (
      isFormFieldValid(meta) && <small className="p-error">{meta.error}</small>
    );
  };

  const dialogFooter = (
    <div className="flex justify-content-center">
      <Button
        label="OK"
        className="p-button-text"
        autoFocus
        onClick={() => setShowMessage(false)}
      />
    </div>
  );
  return (
    <div>
      <main>
        <h1 className="title"> Ajouter un étudiant </h1>
        <div style={{ marginBottom: 40 }}>
          <ul className="breadcrumbs">
            <li>
              <Link to="/">Accueil</Link>
            </li>
            <li className="divider">/</li>
            <li>
              <a href="#" className="active">
                Ajouter un étudiant
              </a>
            </li>
          </ul>
        </div>
        <div className="form-container">
          <Panel header="Formulaire">
            <div className="form">
              <Dialog
                visible={showMessage}
                onHide={() => setShowMessage(false)}
                position="top"
                footer={dialogFooter}
                showHeader={false}
                breakpoints={{ "960px": "80vw" }}
                style={{ width: "30vw" }}
              >
                <div className="flex align-items-center flex-column pt-6 px-3">
                  <i
                    className="pi pi-check-circle"
                    style={{ fontSize: "5rem", color: "var(--green-500)" }}
                  ></i>
                  <h5>Etudiant créé avec succès!</h5>
                </div>
              </Dialog>
              <div className="flex justify-content-center">
                <div className="card">
                  <Form
                    onSubmit={onSubmit}
                    initialValues={{
                      lastName: "",
                      firstName: "",
                      phone: "",
                      email: "",
                      filiere: "",
                      roles: "",
                      login: "",
                      password: "",
                    }}
                    validate={validate}
                    render={({ handleSubmit }) => (
                      <form onSubmit={handleSubmit} className="p-fluid">
                        <Field
                          name="lastName"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="lastName"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="lastName"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Nom*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="firstName"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="firstName"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="firstName"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Prénom*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="phone"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="phone"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="phone"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Tel*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="filiere"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <Dropdown
                                  id="filiere"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                  options={filieres.map((filiere) => {
                                    return {
                                      label: filiere.name,
                                      value: filiere.id,
                                    };
                                  })}
                                />
                                <label
                                  htmlFor="filiere"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Filière*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="roles"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <MultiSelect
                                  id="roles"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                  value={selectRoles}
                                  options={roles.map((role) => {
                                    return { label: role.name, value: role.id };
                                  })}
                                  onChange={(e) => setSelectRoles(e.value)}
                                />
                                <label
                                  htmlFor="roles"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Rôles*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="email"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label p-input-icon-right">
                                <i className="pi pi-envelope" />
                                <InputText
                                  id="email"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="email"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Email*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="login"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="login"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="login"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Login
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="password"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <Password
                                  id="password"
                                  {...input}
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="password"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Password
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Button
                          type="submit"
                          label="Enregistrer"
                          className="mt-2"
                        />
                      </form>
                    )}
                  />
                </div>
              </div>
            </div>
          </Panel>
        </div>
      </main>
    </div>
  );
};

export default AddEtudiant;
