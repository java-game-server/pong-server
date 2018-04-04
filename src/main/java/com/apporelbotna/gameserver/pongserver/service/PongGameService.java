package com.apporelbotna.gameserver.pongserver.service;

public class PongGameService
{
	// TODO uncomment when wsclient is ready
//	private static final String NO_INSTANCE =
//			"No AuthenticatedUser was created. HINT: Use create(user, token) before calling getInstance";
//	private static final String MULTIPLE_INSTANCES =
//			"An AuthenticatedUser was already created. HINT: Did you mean to use getInstance()?";
//
//	private static final Logger logger = LoggerFactory.getLogger(AuthenticatedUser.class);
//
//	private synchronized GameDAO gameDAO;
//	private static PongGameService instance;
//
//	private PongGameService(GameDAO gameDAO)
//	{
//		this.gameDAO = gameDAO;
//	}
//
//	public static PongGameService create(GameDAO gameDAO)
//	{
//		if(instance == null)
//			return new PongGameService(gameDAO);
//		logger.error(MULTIPLE_INSTANCES);
//		throw new UnsupportedOperationException(MULTIPLE_INSTANCES);
//	}
//
//	public static PongGameService getInstance()
//	{
//		if(instance == null)
//		{
//			logger.error(NO_INSTANCE);
//			throw new UnsupportedOperationException(NO_INSTANCE);
//		}
//		return instance;
//	}
//
//	public synchronized void uploadMatchToDatabase(PongGame pongGame)
//	{
//		long gameTime = pongGame.getGameTimeInMillis();
//		Player player1 = pongGame.getPlayer1();
//		Player player2 = pongGame.getPlayer2();
//
//		gameDAO.updateMatch(new Match(
//			player1.getEmail(),
//			ApplicationProperties.getGameId(),
//			gameTime, // CAST TO FLOAT
//			player1.getGoals()));
//
//		gameDAO.updateMatch(new Match(
//				player2.getEmail(),
//				ApplicationProperties.getGameId(),
//				gameTime, // CAST TO FLOAT
//				player2.getGoals()));
//	}
}
