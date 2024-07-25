class LinkedList<T>(
    private var head: Node<T>? = null,
    private var tail: Node<T>? = null,
    private var length: Int = 0
) {

    private fun validateIndex(index: Int) {
        if (index < 0 || index >= length) {
            throw IndexOutOfBoundsException("index $index is not reachable")
        }
    }


    fun add(value: T) {
        val newNode = Node(value, null)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        length++
    }

    fun addFirst(value: T) {
        val newNode = Node(value, null)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head = newNode
        }
        length++
    }

    fun add(index: Int, value: T) {
        validateIndex(index)

        if (index == 0) {
            return addFirst(value)
        }

        if (index == length - 1) {
            return add(value)
        }

        val current = get(index)
        val newNode = Node(value, current?.next)
        current?.next = newNode

        length++
    }

    fun getFirst(): Node<T>? {
        return head
    }

    fun getLast(): Node<T>? {
        return tail
    }

    fun get(index: Int): Node<T>? {
        validateIndex(index)

        if (index == 0) {
            return getFirst()
        }

        if (index == length - 1) {
            return getLast()
        }

        var currentIndex = 0
        var current = head
        while (currentIndex != index) {
            current = current?.next
            currentIndex++
        }

        return current
    }

    fun set(index: Int, value: T): Node<T>? {
        val node = get(index) ?: return null
        node.nodeValue = value
        return node
    }

    fun removeFirst(): Node<T>? {
        val current = head
        head = head?.next
        length--
        return current
    }

    fun removeLast(): Node<T>? {
        val currTail = tail
        val secondLastNode = get(length - 2)
        tail = secondLastNode
        tail?.next = null
        length--
        return currTail
    }

    fun remove(index: Int): Node<T>? {
        validateIndex(index)

        if (index == 0) {
            return removeFirst()
        }

        if (index == length - 1) {
            return removeLast()
        }

        val elementBefore = get(index - 1)
        val element = elementBefore?.next
        val elementAfter = element?.next

        elementBefore?.next = elementAfter

        length--

        return element
    }

    fun contains(value: T): Boolean {
        var current = head

        while (current != null) {
            if (current.nodeValue == value) {
                return true
            }
            current = current.next
        }

        return false
    }

    fun reverse() {
        if (length <= 1) {
            return
        }

        if (length <= 2) {
            val current = head
            current?.next = null
            tail?.next = current
            head = tail
            tail = current
            return
        }

        var current = head
        var prev: Node<T>? = null

        while (current != null) {
            val nextNode = current.next
            current.next = prev
            prev = current
            current = nextNode
        }

        tail = head
        head = prev

    }

    fun clear() {
        head = null
        tail = null
        length = 0
    }

    fun size(): Int {
        return length
    }

    fun print() {
        if (head == null) {
            print("<empty> (size: $length)")
            return
        }

        var current = head
        var position = 0
        while (current != null) {
            val headText = if (current == head) "head-->" else "-->"
            val nullText = if (current == tail) "-->null" else ""
            print("${headText}${current.nodeValue}${nullText}")
            current = current.next
            position++
        }

        print(" (size: $length)")
    }
}

fun main() {
    var ll = LinkedList<Int>()
    ll.add(10)
    ll.add(2)
    ll.reverse()
    ll.print()

}
