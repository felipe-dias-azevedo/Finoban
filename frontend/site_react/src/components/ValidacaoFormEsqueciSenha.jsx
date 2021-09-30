export default function validate(values) {
    let errors = {};
    if (!values.email) {
      errors.email = 'Insira um endereço de e-mail válido';
    } else if (!/\S+@\S+\.\S+/.test(values.email)) {
      errors.email = 'Endereço de e-mail inválido!';
    }
    
    return errors;
  };