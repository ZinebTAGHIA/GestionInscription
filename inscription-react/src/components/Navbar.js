import React from "react";
import logo from "../logos/logo-ensaj.png";

const Navbar = (props) => {
  const handleToggleSidebarClick = () => {
    const allSideDivider = document.querySelectorAll("#sidebar .divider");
    if (props.isSidebarHiden == false) {
      allSideDivider.forEach((item) => {
        item.textContent = "-";
      });
    } else {
      allSideDivider.forEach((item) => {
        item.textContent = item.dataset.text;
      });
    }
    props.onHidenSidebarChange(!props.isSidebarHiden);
    document.querySelectorAll(".side-dropdown").forEach((dropdown) => {
      dropdown.classList.remove("show");
    });
  };

  return (
    <nav>
      <i
        className="bx bx-menu toggle-sidebar"
        onClick={handleToggleSidebarClick}
      />
      <div className="titleContainer">
        <img src={logo} alt="logo ensaj" />
        <h1 className="title">Inscription</h1>
      </div>

      <span className="divider" />
      <div className="profile">
        <img
          src={"https://cdn-icons-png.flaticon.com/512/149/149071.png"}
          alt="Photo"
        />
      </div>
    </nav>
  );
};

export default Navbar;
