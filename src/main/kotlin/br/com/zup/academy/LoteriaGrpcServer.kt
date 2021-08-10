package br.com.zup.academy

import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class LoteriaGrpcServer : LoteriaServiceGrpc.LoteriaServiceImplBase() {

    private val logger = LoggerFactory.getLogger(LoteriaGrpcServer::class.java)

    override fun sortear(request: LoteriaRequest?, responseObserver: StreamObserver<LoteriaResponse>?) {
        logger.info("Sortear números para: ${request?.nome}")

        val numeros = mutableListOf<Int>()

        for (i in 1..6) {
            numeros.add(Random.nextInt(from = 0, until = 60))
        }

        val response = LoteriaResponse
            .newBuilder()
            .setNome(request?.nome)
            .setNumeros(numeros.toString())
            .build()

        responseObserver!!.onNext(response)
        responseObserver!!.onCompleted()
    }
}