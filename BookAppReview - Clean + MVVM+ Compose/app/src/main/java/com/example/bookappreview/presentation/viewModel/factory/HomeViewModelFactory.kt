//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
//import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
//import com.example.bookappreview.presentation.viewModel.HomeViewModel
//
//class HomeViewModelFactory(
//    private val usecase: BuscarLivrosUseCase
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(usecase) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
