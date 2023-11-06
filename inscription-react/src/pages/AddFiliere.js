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
import "primereact/resources/themes/lara-light-indigo/theme.css"; //theme
import "primereact/resources/primereact.min.css";

const AddFiliere = () => {
  const [showMessage, setShowMessage] = useState(false);
  const [formData, setFormData] = useState({});

  const validate = (data) => {
    let errors = {};

    if (!data.code) {
      errors.code = "Le code est obligatoire.";
    }
    if (!data.name) {
      errors.name = "L'intitulé est obligatoire.";
    }
    // if (!data.cin) {
    //   errors.cin = "CIN est obligatoire.";
    // }
    // if (!data.cne) {
    //   errors.cne = "CNE est obligatoire.";
    // }
    // if (data.email) {
    //   if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(data.email)) {
    //     errors.email = "Adresse email invalide. E.g. example@email.com";
    //   }
    // }
    return errors;
  };

  const onSubmit = (data, form) => {
    setFormData(data);
    if (data) {
      axios
        .post(`/api/filieres`, {
          code: data.code,
          name: data.name,
          // CIN: data.cin,
          // CNE: data.cne,
          // email: data.email,
          // tel: data.tel,
          // role: "Etudiant",
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
      setShowMessage(true);
    }
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
        <h1 className="title"> Ajouter une filière </h1>
        <div style={{ marginBottom: 40 }}>
          <ul className="breadcrumbs">
            <li>
              <Link to="/">Accueil</Link>
            </li>
            <li className="divider">/</li>
            <li>
              <a href="#" className="active">
                Ajouter une filière
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
                  <h5>Filière créée avec succès!</h5>
                </div>
              </Dialog>
              <div className="flex justify-content-center">
                <div className="card">
                  <Form
                    onSubmit={onSubmit}
                    initialValues={{
                      code: "",
                      name: "",
                    }}
                    validate={validate}
                    render={({ handleSubmit }) => (
                      <form onSubmit={handleSubmit} className="p-fluid">
                        <Field
                          name="code"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="code"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="code"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Code*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="name"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="name"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="name"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Intitulé*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        {/* <Field
                          name="cin"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="cin"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="cin"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  CIN*
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="cne"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="cne"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="cne"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  CNE*
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
                                  Email
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        />
                        <Field
                          name="tel"
                          render={({ input, meta }) => (
                            <div className="field">
                              <span className="p-float-label">
                                <InputText
                                  id="tel"
                                  {...input}
                                  autoFocus
                                  className={classNames({
                                    "p-invalid": isFormFieldValid(meta),
                                  })}
                                />
                                <label
                                  htmlFor="tel"
                                  className={classNames({
                                    "p-error": isFormFieldValid(meta),
                                  })}
                                >
                                  Tel
                                </label>
                              </span>
                              {getFormErrorMessage(meta)}
                            </div>
                          )}
                        /> */}
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

export default AddFiliere;
