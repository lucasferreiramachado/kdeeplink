package com.lucasferreiramachado.kdeeplink.example.multiplatform.di

import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.AuthCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.ProductsCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.PurchaseProductCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ProductListCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.home.HomeCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.FakeUserRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.UserRepository

class AuthCoordinatorFactory {
    fun create(
        parent: KCoordinator<*>,
    ): AuthCoordinator = AuthCoordinator(
        parent
    )
}

class HomeCoordinatorFactory {
    fun create(
        parent: KCoordinator<*>
    ): HomeCoordinator = HomeCoordinator(
        parent
    )
}

class ProductsCoordinatorFactory {
    fun create(parent: KCoordinator<*>): ProductsCoordinator = ProductsCoordinator(parent = parent)
}

class PurchaseProductCoordinatorFactory {
    fun create(parent: KCoordinator<*>): PurchaseProductCoordinator = PurchaseProductCoordinator(parent)
}

class ProductListCoordinatorFactory {
    fun create(parent: KCoordinator<*>): ProductListCoordinator = ProductListCoordinator(parent)
}

class FeaturesCoordinatorFactory(
) {
    fun create(parent: KCoordinator<*>): FeaturesCoordinator {
        return FeaturesCoordinator(parent = parent)
    }
}

class DeeplinkCoordinatorFactory(
) {
    fun create(parent: FeaturesCoordinator): DeeplinkCoordinator {
        return DeeplinkCoordinator(parent = parent)
    }
}

object UserRepositoryFactory {
    private val repository: UserRepository = FakeUserRepository()

    fun create(): UserRepository {
        return repository
    }
}

object PurchaseRepositoryFactory {
    private val repository: PurchaseRepository = FakePurchaseRepository()

    fun create(): PurchaseRepository {
        return repository
    }
}
