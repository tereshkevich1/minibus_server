package com.example.dao

import com.example.model.RoutD

interface RouteDAO {
    suspend fun allRoutes(): List<RoutD>
    suspend fun route(startingLocationId: Int, finalLocationId: Int): RoutD?
    suspend fun addNewRoute(startingLocationId: Int, finalLocationId: Int): RoutD?
    suspend fun editRoute(id: Int, name: String): Boolean
    suspend fun deleteRoute(id: Int): Boolean
}

