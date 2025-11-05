package ir.khanbeiki.screens

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import ir.khanbeiki.db.MainRepository
import ir.khanbeiki.sqldelight.sample.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository,
) : ScreenModel {

    private val _getAllUsers = MutableStateFlow<List<User>?>(null)
    val getAllUsers: StateFlow<List<User>?> = _getAllUsers

    private val _getUserById = MutableStateFlow<User?>(null)
    val getUserById: StateFlow<User?> = _getUserById

    fun insertUser(name: String, email: String) {
        screenModelScope.launch {
            try {
                repository.insertUser(name, email)
            } catch (e: Exception) {
            }
        }
    }

    fun getAllUsers() {
        screenModelScope.launch {
            try {
                _getAllUsers.value = repository.getAllUsers()
            } catch (e: Exception) {

            } finally {

            }
        }
    }

    fun getUserById(id: Long) {
        screenModelScope.launch {
            try {
                _getUserById.value = repository.getUserById(id)
            } catch (e: Exception) {

            } finally {

            }
        }
    }

    fun deleteUser(id: Long) {
        screenModelScope.launch {
            try {
                repository.deleteUser(id)
            } catch (e: Exception) {

            } finally {

            }
        }
    }
}