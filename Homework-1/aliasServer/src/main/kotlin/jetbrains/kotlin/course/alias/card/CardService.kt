package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService {
    private val identifierFactory = IdentifierFactory()
    private val words = listOf("apple", "banana", "cherry", "date", "elephant", "fox", "grape", "hat")

    companion object {
        const val WORDS_IN_CARD = 4
        val cardsAmount: Int
            get() = words.size / WORDS_IN_CARD
    }

    val cards: List<Card> = generateCards()

    private fun List<String>.toWords(): List<Word> = map { Word(it) }

    private fun generateCards(): List<Card> {
        return words.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { Card(identifierFactory.uniqueIdentifier(), it.toWords()) }
    }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index) ?: throw IllegalArgumentException("Card index out of bounds")
    }
}

