export default function validate(values) {
    let errors = {};
    if (!values.email) {
      errors.email = 'Insira um endereço de e-mail válido';
    } else if (!/\S+@\S+\.\S+/.test(values.email)) {
      errors.email = 'Endereço de e-mail inválido!';
    }
    
    if (!values.password) {
      errors.password = 'Insira uma senha';
    } else if (values.password.length < 6) {
      errors.password = 'Insira uma senha com no mínimo 6 caracteres';
    }

    return errors;
  };