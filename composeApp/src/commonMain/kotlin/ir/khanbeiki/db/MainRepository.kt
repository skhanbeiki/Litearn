package ir.khanbeiki.db

import ir.khanbeiki.sqldelight.sample.data.Database
import ir.khanbeiki.sqldelight.sample.data.User

interface MainRepository {
    suspend fun insertUser(name: String, email: String): Boolean
    suspend fun getAllUsers(): List<User>?
    suspend fun getUserById(id: Long): User?
    suspend fun deleteUser(id: Long): Boolean
}

class MainRepositoryImpl(databaseFactory: DatabaseFactory) : MainRepository {

    private val database = Database(databaseFactory.createDriver())
    private val queries = database.userQueries

    override suspend fun insertUser(name: String, email: String): Boolean {
        return try {
            queries.insertUser(name, email)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getUserById(id: Long): User? {
        return try {
            queries.selectUserById(id).executeAsOneOrNull()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllUsers(): List<User>? {
        return try {
            queries.getAllUsers().executeAsList()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteUser(id: Long): Boolean {
        return try {
            queries.deleteUserById(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}



