import { useEffect } from "react";
import { useHistory } from "react-router";
import Login from "./Login";

function NotFound() {

  const history = useHistory();

  useEffect(() => {
    history.push('/login');
  }, [history]);

  return (
    <Login />
  );
}

export default NotFound;