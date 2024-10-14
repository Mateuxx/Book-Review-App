# Jetpack Compose Notes

### Modifier.Weight(1f)

- o Weight é usado para que um componente dentro de um layout Flexivel(colum no caso de MainScreen)
  ocupe mais ou menos espaço dependendo de como quisermos.
- Sem o **Weight(1f**) o colum tenta fazer o máximo possivel para que possamos colocar os items
  ocupados na tela. Com o weight direcionamos, as telas ligados ao nav host, agora que ele ocupa
  todo o espaço destinado a ele e o customBottomNavigation pode ficar abaixo e fixo no fim da coluna
- O weight(1f) garante que o NavHost se ajuste dinamicamente ao espaço, e não seja limitado por uma
  altura fixa ou insuficiente.
