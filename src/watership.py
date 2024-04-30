import os
import discord
import dotenv

dotenv.load_dotenv()

bot = discord.Bot(intents=discord.Intents.all())

@bot.event
async def on_ready():
    print(f"{bot.user} is ready and online!")


@bot.event
async def on_member_join(member):
    await member.send(f"Welcome to the server, {member.mention}! Enjoy your stay here.")
    member.give_role(3)



@bot.slash_command(name="hello", description="Say hello to the bot")
async def hello(ctx: discord.ApplicationContext):
    await ctx.respond("Hey!")

@bot.event
async def on_message(message):
    if (message.content == "ping"):
        await message.delete()

bot.run(os.getenv("TOKEN"))
