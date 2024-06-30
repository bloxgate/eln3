package org.eln.eln3.i18n

import net.minecraft.client.resources.language.I18n
import org.eln.eln3.misc.Utils

/**
 * Internationalization and localization helper class.
 */
object I18N {

    @JvmOverloads
    fun encodeLangKey(key: String, replaceWhitespaces: Boolean = true): String {
        var keyChanged = key
        if (replaceWhitespaces) {
            keyChanged = keyChanged.replace(' ', '_')
        }
        return keyChanged.replace("=", "\\=")
            .replace(":", "\\:")
            .replace("\n", "\\n")
            .replace("/", "_")
    }

    /**
     * Translates the given string. You can pass arguments to the method and reference them in the string using
     * the placeholders %N$ whereas N is the index of the actual parameter **starting at 1**.
     *
     *
     * Example: tr("You have %1$ lives left", 4);
     *
     *
     * IT IS IMPORTANT THAT YOU PASS THE **STRING LITERALS** AT LEAST ONCE AS THE FIRST PARAMETER TO THIS METHOD or
     * you call the method TR() with the actual string literal in order to register the translation text automatically!
     * Otherwise the translation will not be added to the language files. There is no problem to use the tr() method
     * afterwards using an already registered string in the code using a string variable as the first parameter.
     *
     *
     *
     * @param text    Text to translate
     * @param objects Arguments to integrate into the text.
     * @return Translated text or original text (Argument placeholders are replaced by the actual arguments
     * anyway) if no translation is present.
     */
    fun tr(text: String, vararg objects: Any): String? {
        // Try to find the translation for the string using forge API.
        var translation: String? = null
        try {
            translation = I18n.get(text)
        } catch (ignored: NullPointerException) {
            Utils.println("Unable to translate string: $text")
        }

        // If no translation was found, just use the original text.
        translation = if (translation == null || "" == translation) {
            text
        } else {
            // Replace placeholders .
            translation.replace("\\n", "\n").replace("\\:", ":")
        }

        // Replace placeholders in string by actual string values of the passed objects.
        for (i in objects.indices) {
            translation = translation!!.replace("%" + (i + 1) + "$", objects[i].toString())
        }

        return translation
    }

    /**
     * This method can be used to mark an unlocalized text in order to add it to the generated language files.
     * The method does not actually translate the text - it marks the text literal only to be translated afterwards.
     * A common use case is to add text to the language file which is translated using a text variable with the
     * method tr().
     *
     * @param text String LITERAL to add to the language files.
     * @return Exactly the same text as given to the method.
     */
    fun TR(text: String): String {
        return encodeLangKey(text)
    }

    /**
     * Used to register a name to translate. The forge mechanisms are used in order to translate the name.
     *
     * @param type Type the translatable name is related to.
     * @param text String LITERAL to register for translation.
     * @return Returns the same text literal, forge will translate the name magically.
     */
    fun TR_NAME(type: Type, text: String): String {
        return if (type.isEncodedAtRuntime) {
            StringBuilder(type.prefix).append(encodeLangKey(text)).append(".name").toString()
        } else {
            text
        }
    }

    /**
     * Used to register a description to translate. The forge mechanisms are used in order to translate the description.
     *
     * @param type Type the translatable description is related to.
     * @param text String LITERAL to register for translation.
     * @return Returns the same text literal, forge will translate the description magically.
     */
    fun TR_DESC(type: Type, text: String): String {
        return if (type.isEncodedAtRuntime) {
            StringBuilder(type.prefix).append(encodeLangKey(text)).append(".desc").toString()
        } else {
            text
        }
    }

    /**
     * Defines the different translatable types.
     */
    enum class Type(
        /**
         * Returns the prefix.
         *
         * @return Prefix for the type of translatable text.
         */
        val prefix: String, val isEncodedAtRuntime: Boolean, val isWhitespacesInFileReplaced: Boolean
    ) {
        /**
         * The text to translate is not related to a particular translatable type, so basically only the ".name" suffix
         * is added to the translation key.
         */
        NONE("", false, true),

        /**
         * The text to translate is related to an item. The "item." runtimePrefix will be added to the translation key.
         */
        ITEM("item.", false, false),

        /**
         * The text to translate is related to a tile. The "tile." runtimePrefix will be added to the translation key.
         */
        TILE("tile.", false, false),

        /**
         * The text to translate is related to an achievement. The "achievement." runtimePrefix will be added to the
         * translation key.
         */
        ACHIEVEMENT("achievement.", true, true),

        /**
         * The text to translate is related to an entity. The "entity." runtimePrefix will be added to the translation key.
         */
        ENTITY("entity.", false, false),

        /**
         * The text to translate is related to a death attack. The "death.attack" runtimePrefix will be added to the
         * translation key.
         */
        DEATH_ATTACK("death.attack.", false, false),

        /**
         * The text to translate is related to an item group. The "itemGroup." runtimePrefix will be added to the translation
         * key.
         */
        ITEM_GROUP("itemGroup.", false, false),

        /**
         * The text to translate is related to a container. The "container." runtimePrefix will be added to the translation
         * key.
         */
        CONTAINER("container.", false, false),

        /**
         * The text to translate is related to an block. The "block." runtimePrefix will be added to the translation key.
         */
        BLOCK("block.", false, false),

        SIX_NODE("eln.sixnode.", false, true),

        NODE("eln.node.", false, true)
    }
}