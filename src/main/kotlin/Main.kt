import com.xatkit.core.XatkitBot
import com.xatkit.dsl.DSL.*
import com.xatkit.plugins.react.platform.ReactPlatform
import com.xatkit.plugins.react.platform.io.ReactEventProvider
import org.apache.commons.configuration2.BaseConfiguration
import org.apache.commons.configuration2.Configuration


object Main {
    @JvmStatic
    fun main(args: Array<String>) {

        val greetings = intent("Greetings")
            .trainingSentence("Hi")
            .trainingSentence("Hello")
            .trainingSentence("Good morning")
            .trainingSentence("Good afternoon")

        val howAreYou = intent("HowAreYou")
            .trainingSentence("How are you?")
            .trainingSentence("What's up?")
            .trainingSentence("How do you feel?")

        val reactPlatform = ReactPlatform()
        val reactEventProvider = reactPlatform.reactEventProvider
        val reactIntentProvider = reactPlatform.reactIntentProvider

        val init = state("Init")
        val awaitingInput = state("AwaitingInput")
        val handleWelcome = state("HandleWelcome")
        val handleWhatsUp = state("HandleWhatsUp")

        init.next().`when`(eventIs(ReactEventProvider.ClientReady)).moveTo(awaitingInput)

        awaitingInput.next().`when`(intentIs(greetings)).moveTo(handleWelcome)
            .`when`(intentIs(howAreYou)).moveTo(handleWhatsUp)

        handleWelcome.body { context -> reactPlatform.reply(context, "Hi, nice to meet you!") }
            .next().moveTo(awaitingInput)

        handleWhatsUp.body { context -> reactPlatform.reply(context, "I am fine and you?") }
            .next().moveTo(awaitingInput)

        val defaultFallback = fallbackState().body { context ->
            reactPlatform.reply(context, "Sorry, I didn't, get it")
        }

        val botModel = model()
            .usePlatform(reactPlatform)
            .listenTo(reactEventProvider)
            .listenTo(reactIntentProvider)
            .initState(init)
            .defaultFallbackState(defaultFallback)

        val botConfiguration: Configuration = BaseConfiguration()
        val bot = XatkitBot(botModel, botConfiguration)
        bot.run()
    }
}