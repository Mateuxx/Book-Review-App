import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.viewModel.HomeViewModel

class HomeViewModelFactory(
    private val repository: UsuarioRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
