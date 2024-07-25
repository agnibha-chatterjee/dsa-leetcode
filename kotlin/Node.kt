package dsa

data class Node<T>(var nodeValue: T, var next: Node<T>?) {
    fun print() {
        print("[nodeValue=$nodeValue, next=$next]")
    }
}