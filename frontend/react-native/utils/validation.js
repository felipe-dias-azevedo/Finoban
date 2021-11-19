export const nameValidator = (name) => {
  if (!name || name.length < 6) return 'Insira seu nome completo.';
  if (typeof name !== 'string') return 'Insira um nome válido';
};

export const cpfValidator = (cpf) => {
  var regexCpf = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;

  if (!cpf) return 'Insira o seu CPF.';
  if (!regexCpf.test(cpf)) return 'CPF inválido.';
};

export const emailValidator = (email) => {
  const regexEmail = /\S+@\S+\.\S+/;

  if (!email || email.length <= 0) return 'Insira o seu endereço de e-mail.';
  if (!regexEmail.test(email)) return 'Endereço de e-mail inválido.';
};

export const passwordValidator = (password) => {
  if (!password || password.length <= 0) return 'Insira sua senha de acesso.';
  if (password.length < 6) return 'Insira uma senha de no minímo 6 caracteres.';
};

export const rendaValidator = (renda) => {
  if (!renda || renda.length <= 0) return 'Insira o valor de sua renda mensal';
};

export const valorImovelValidator = (value) => {
  if (!value || value.length <= 0) return 'Insira o valor do imóvel que deseja financiar';
};

export const porcentagemRendaValidator = (porcentagem) => {
  if (!porcentagem || porcentagem.length <= 0) return 'Insira a porcentagem de sua renda destinada para o financiamento';
};

export const tempoFinanciamentoValidator = (tempoFinanciamento) => {
  if (!tempoFinanciamento || tempoFinanciamento.length <= 0) return 'Insira o tempo de financiamento desejado';
};