# Jetpack Compose Notes

### Modifier.Weight(1f)

- o Weight é usado para que um componente dentro de um layout Flexivel(colum no caso de MainScreen)
  ocupe mais ou menos espaço dependendo de como quisermos.
- Sem o **Weight(1f**) o colum tenta fazer o máximo possivel para que possamos colocar os items
  ocupados na tela. Com o weight direcionamos, as telas ligados ao nav host, agora que ele ocupa
  todo o espaço destinado a ele e o customBottomNavigation pode ficar abaixo e fixo no fim da coluna
- O weight(1f) garante que o NavHost se ajuste dinamicamente ao espaço, e não seja limitado por uma
  altura fixa ou insuficiente.

### Ui State e ViewModel:

- O **_UI State_** (Estado da Interface) é uma representação dos dados e da aparência que a interface
  gráfica (UI) precisa exibir a qualquer momento. Ele inclui informações como:

- Quais dados a tela deve mostrar (livros, lista de filmes, reviews).
- Se algo está sendo carregado ou não (indicadores de carregamento).
- Mensagens de erro, caso ocorram.
- Estados como qual aba está ativa ou qual item está selecionado.

- O **_ViewModel_** gerencia a lógica e as operações que alteram o UI State. Ele é responsável por:

- Carregar os dados (por exemplo, ao buscar livros de uma API).
- Lidar com eventos de UI, como cliques, alterações de abas, etc.
- Atualizar o UI State conforme a lógica de negócios.
- Garantir que os dados sobrevivam a mudanças de configuração, como rotação de tela.
- O ViewModel contém a lógica e manipula o estado, mas não deve conhecer detalhes da interface gráfica.