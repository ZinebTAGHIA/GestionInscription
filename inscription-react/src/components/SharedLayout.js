import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";

const SharedLayout = (props) => {
  const [isSidebarHiden, setIsSidebarHiden] = useState(true);

  const handleHidenChange = (isHiden) => {
    setIsSidebarHiden(isHiden);
  };

  return (
    <>
      <Sidebar isSidebarHiden={isSidebarHiden} />
      <section id="content">
        <Navbar
          isSidebarHiden={isSidebarHiden}
          onHidenSidebarChange={handleHidenChange}
        />
        <Outlet />
      </section>
    </>
  );
};

export default SharedLayout;
