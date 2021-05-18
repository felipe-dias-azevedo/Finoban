import { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router";
import api from "../services/api";

function Detail() {

  const params = useParams();
  const [dadosUsuario, setDadosUsuario] = useState({
    cnpj: "",
    nome: "",
    email: "",
    cep: "",
    numero: "",
    dataNasc: ""
  });

  useEffect(() => {
    api.get(`/usuarios/${params.id}`, {}, {
      'Access-Control-Allow-Origin': '*',
    }).then(e => {

      const usuario = e.data;
      console.log(usuario);
      setDadosUsuario(usuario);

      // const novoUsuario = { ...dadosUsuario };
      // novoUsuario[dadosUsuario.cnpj] = e.data.cnpj;
      // novoUsuario[dadosUsuario.nome] = e.data.nome;
      // novoUsuario[dadosUsuario.email] = e.data.email;
      // novoUsuario[dadosUsuario.cep] = e.data.cep;
      // novoUsuario[dadosUsuario.numero] = e.data.numero;
      // novoUsuario[dadosUsuario.dataNasc] = e.data.dataNasc;

      // setDadosUsuario(novoUsuario)

    }).catch(e => {
      console.error(e);
    });
  }, [params.id]);

  return (
    <>

    </>
  );
}

export default Detail;